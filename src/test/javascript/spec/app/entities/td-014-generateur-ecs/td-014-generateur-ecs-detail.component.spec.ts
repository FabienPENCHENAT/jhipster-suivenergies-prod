import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD014GenerateurECSDetailComponent } from 'app/entities/td-014-generateur-ecs/td-014-generateur-ecs-detail.component';
import { TD014GenerateurECS } from 'app/shared/model/td-014-generateur-ecs.model';

describe('Component Tests', () => {
  describe('TD014GenerateurECS Management Detail Component', () => {
    let comp: TD014GenerateurECSDetailComponent;
    let fixture: ComponentFixture<TD014GenerateurECSDetailComponent>;
    const route = ({ data: of({ tD014GenerateurECS: new TD014GenerateurECS(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD014GenerateurECSDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD014GenerateurECSDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD014GenerateurECSDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD014GenerateurECS on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD014GenerateurECS).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
